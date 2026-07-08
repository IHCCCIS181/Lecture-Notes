<script lang="ts">
	import './layout.css';
	import favicon from '$lib/assets/favicon.svg';
	import { onMount } from 'svelte';
	import { clearCurrentUser, readCurrentUser, type User } from '$lib/fundspark';
	import { goto } from '$app/navigation';

	let currentUser = $state<User | null>(null);

	onMount(() => {
		currentUser = readCurrentUser();
	});

	async function signOut() {
		clearCurrentUser();
		currentUser = null;
		await goto('/');
	}

	let { children } = $props();
</script>

<svelte:head><link rel="icon" href={favicon} /></svelte:head>

<div class="shell">
	<header class="topbar">
		<nav class="nav">
			<a href="/">Home</a>
			<a href="/users">Users</a>
			<a href="/fundraisers">Fundraisers</a>
		</nav>
		<div class="user-chip">
			{#if currentUser}
				<span>{currentUser.username}</span>
				<button type="button" onclick={signOut}>Sign out</button>
			{:else}
				<span>No user</span>
			{/if}
		</div>
	</header>

	<main class="content">
		{@render children()}
	</main>
</div>
