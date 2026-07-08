<script lang="ts">
	import { goto } from '$app/navigation';
	import { onMount } from 'svelte';
	import { ensureUser, readCurrentUser, writeCurrentUser } from '$lib/fundspark';

	let username = $state('');
	let password = $state('');
	let statusMessage = $state('');
	let submitting = $state(false);

	onMount(() => {
		if (readCurrentUser()) {
			void goto('/fundraisers');
		}
	});

	async function handleLogin() {
		submitting = true;
		statusMessage = '';

		try {
			const user = await ensureUser(username.trim(), password);
			writeCurrentUser(user);
			await goto('/fundraisers');
		} catch (error) {
			statusMessage = error instanceof Error ? error.message : 'Unable to log in';
		} finally {
			submitting = false;
		}
	}
</script>

<svelte:head>
	<title>FundSpark</title>
</svelte:head>

<section class="page">
	<h1>FundSpark</h1>
	<p>Enter a username to continue. If the user does not exist, it will be created.</p>

	<form class="panel" onsubmit={(event) => {
		event.preventDefault();
		void handleLogin();
	}}>
		<label>
			<span>Username</span>
			<input bind:value={username} autocomplete="username" />
		</label>

		<label>
			<span>Password</span>
			<input bind:value={password} type="password" autocomplete="current-password" />
		</label>

		<button type="submit" disabled={submitting}>{submitting ? 'Loading...' : 'Continue'}</button>
	</form>

	{#if statusMessage}
		<p>{statusMessage}</p>
	{/if}
</section>
