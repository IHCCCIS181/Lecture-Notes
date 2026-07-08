<script lang="ts">
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import {
		createFundraiserRecord,
		loadFundraisers,
		readCurrentUser,
		type Fundraiser,
	} from '$lib/fundspark';

	let fundraisers = $state<Fundraiser[]>([]);
	let currentUser = $state(readCurrentUser());
	let title = $state('');
	let description = $state('');
	let targetAmount = $state('');
	let statusMessage = $state('');

	onMount(async () => {
		currentUser = readCurrentUser();
		if (!currentUser) {
			await goto('/');
			return;
		}

		fundraisers = await loadFundraisers();
	});

	async function createFundraiser() {
		if (!currentUser) {
			await goto('/');
			return;
		}

		const fundraiser = await createFundraiserRecord({
			title,
			description,
			owner: currentUser,
			startDate: new Date().toISOString(),
			endDate: new Date(Date.now() + 1000 * 60 * 60 * 24 * 30).toISOString(),
			targetAmount: Number(targetAmount),
			currentAmount: 0,
			comments: [],
		});

		fundraisers = [fundraiser, ...fundraisers];
		statusMessage = `Created ${fundraiser.title ?? 'fundraiser'}`;
		title = '';
		description = '';
		targetAmount = '';
	}
</script>

<svelte:head>
	<title>FundSpark Fundraisers</title>
</svelte:head>

<section class="page">
	<h1>Fundraisers</h1>

	{#if currentUser}
		<p>Signed in as {currentUser.username}</p>
	{/if}

	<form class="panel" onsubmit={(event) => {
		event.preventDefault();
		void createFundraiser();
	}}>
		<label>
			<span>Title</span>
			<input bind:value={title} />
		</label>

		<label>
			<span>Description</span>
			<textarea bind:value={description} rows="3"></textarea>
		</label>

		<label>
			<span>Target amount</span>
			<input bind:value={targetAmount} type="number" min="1" step="1" />
		</label>

		<button type="submit">Create fundraiser</button>
	</form>

	{#if statusMessage}
		<p>{statusMessage}</p>
	{/if}

	<div class="stack">
		{#each fundraisers as fundraiser}
			<article class="panel">
				<a href={`/fundraisers/${fundraiser.id}`}>
					<strong>{fundraiser.title ?? 'Untitled fundraiser'}</strong>
				</a>
				<p>{fundraiser.description}</p>
			</article>
		{/each}
	</div>
</section>