<script lang="ts">
	import { onMount } from 'svelte';
	import { ensureUser, loadUsers, type User } from '$lib/fundspark';

	let users = $state<User[]>([]);
	let username = $state('');
	let password = $state('');
	let statusMessage = $state('');

	onMount(async () => {
		users = await loadUsers();
	});

	async function addUser() {
		statusMessage = '';
		const user = await ensureUser(username.trim(), password);
		if (!users.some((entry) => entry.id === user.id)) {
			users = [...users, user];
		}
		statusMessage = `Saved ${user.username}`;
		username = '';
		password = '';
	}
</script>

<svelte:head>
	<title>FundSpark Users</title>
</svelte:head>

<section class="page">
	<h1>Users</h1>

	<form class="panel" onsubmit={(event) => {
		event.preventDefault();
		void addUser();
	}}>
		<label>
			<span>Username</span>
			<input bind:value={username} />
		</label>

		<label>
			<span>Password</span>
			<input bind:value={password} type="password" />
		</label>

		<button type="submit">Create or reuse user</button>
	</form>

	{#if statusMessage}
		<p>{statusMessage}</p>
	{/if}

	<div class="stack">
		{#each users as user}
			<article class="panel">
				<strong>{user.username}</strong>
				<span>{user.id}</span>
			</article>
		{/each}
	</div>
</section>