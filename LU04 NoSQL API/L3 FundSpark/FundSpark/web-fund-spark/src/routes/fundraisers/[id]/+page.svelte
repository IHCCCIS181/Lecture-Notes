<script lang="ts">
	import { onMount } from 'svelte';
	import { page } from '$app/state';
	import {
		createComment,
		loadFundraiser,
		loadFundraiserComments,
		readCurrentUser,
		type Comment,
		type Fundraiser,
	} from '$lib/fundspark';

	let fundraiser = $state<Fundraiser | null>(null);
	let comments = $state<Comment[]>([]);
	let commentText = $state('');
	let currentUser = $state(readCurrentUser());
	let statusMessage = $state('');

	onMount(() => {
		const fundraiserId = page.params.id;
		if (!fundraiserId) {
			return;
		}
		void loadFundraiser(fundraiserId).then((loadedFundraiser) => {
			fundraiser = loadedFundraiser;
		});
		void loadFundraiserComments(fundraiserId).then((loadedComments) => {
			comments = loadedComments;
		});
	});

	async function submitComment() {
		if (!currentUser || !fundraiser?.id) {
			statusMessage = 'You need a user first.';
			return;
		}

		const savedComment = await createComment(fundraiser.id, {
			text: commentText,
			authorUsername: currentUser.username,
		});

		comments = [...comments, savedComment];
		commentText = '';
		statusMessage = 'Comment added.';
	}
</script>

<svelte:head>
	<title>{fundraiser?.title ?? 'Fundraiser'}</title>
</svelte:head>

<section class="page">
	{#if fundraiser}
		<h1>{fundraiser.title}</h1>
		<p>{fundraiser.description}</p>
		<p>Target: {fundraiser.targetAmount}</p>
		<p>Raised: {fundraiser.currentAmount}</p>
	{:else}
		<p>Loading fundraiser...</p>
	{/if}

	<section class="panel">
		<h2>Comments</h2>

		<form class="panel" onsubmit={(event) => {
			event.preventDefault();
			void submitComment();
		}}>
			<label>
				<span>Comment</span>
				<textarea bind:value={commentText} rows="3"></textarea>
			</label>
			<button type="submit">Add comment</button>
		</form>

		{#if statusMessage}
			<p>{statusMessage}</p>
		{/if}

		<div class="stack">
			{#each comments as comment}
				<article class="panel">
					<strong>{comment.authorUsername}</strong>
					<p>{comment.text}</p>
				</article>
			{/each}
		</div>
	</section>
</section>