import { browser } from '$app/environment';
import {
    addComment,
    createUser,
    createFundraiser,
    getAllFundraisers,
    getAllUsers,
    getComments1,
    getFundraiserById,
} from '$lib/api';
import type { Comment, Fundraiser, User } from '$lib/api';

export type { Comment, Fundraiser, User } from '$lib/api';

const CURRENT_USER_KEY = 'fundspark.currentUser';

function unwrapResult<T>(result: T | { data: T }): T {
    if (result && typeof result === 'object' && 'data' in result) {
        return result.data;
    }

    return result;
}

export function readCurrentUser(): User | null {
    if (!browser) {
        return null;
    }

    const rawUser = localStorage.getItem(CURRENT_USER_KEY);
    if (!rawUser) {
        return null;
    }

    try {
        return JSON.parse(rawUser) as User;
    } catch {
        localStorage.removeItem(CURRENT_USER_KEY);
        return null;
    }
}

export function writeCurrentUser(user: User): void {
    if (!browser) {
        return;
    }

    localStorage.setItem(CURRENT_USER_KEY, JSON.stringify(user));
}

export function clearCurrentUser(): void {
    if (!browser) {
        return;
    }

    localStorage.removeItem(CURRENT_USER_KEY);
}

export async function ensureUser(
    username: string,
    password: string,
): Promise<User> {
    const usersResponse = await getAllUsers({
        responseStyle: 'data',
        throwOnError: true,
    });
    const users = unwrapResult(usersResponse);
    const existingUser = users.find((user) => user.username === username);

    if (existingUser) {
        return existingUser;
    }

    const createdUser = await createUser({
        body: {
            username,
            password,
        },
        responseStyle: 'data',
        throwOnError: true,
    });

    return unwrapResult(createdUser);
}

export async function loadUsers(): Promise<User[]> {
    const response = await getAllUsers({
        responseStyle: 'data',
        throwOnError: true,
    });
    return unwrapResult(response);
}

export async function loadFundraisers(): Promise<Fundraiser[]> {
    const response = await getAllFundraisers({
        responseStyle: 'data',
        throwOnError: true,
    });
    return unwrapResult(response);
}

export async function createFundraiserRecord(
    fundraiser: Omit<Fundraiser, 'id'>,
): Promise<Fundraiser> {
    const response = await createFundraiser({
        body: fundraiser as Fundraiser,
        responseStyle: 'data',
        throwOnError: true,
    });

    return unwrapResult(response);
}

export async function loadFundraiser(id: string): Promise<Fundraiser> {
    const response = await getFundraiserById({
        path: { id },
        responseStyle: 'data',
        throwOnError: true,
    });

    return unwrapResult(response);
}

export async function loadFundraiserComments(id: string): Promise<Comment[]> {
    const response = await getComments1({
        path: { id },
        responseStyle: 'data',
        throwOnError: true,
    });

    return unwrapResult(response);
}

export async function createComment(
    fundraiserId: string,
    comment: Comment,
): Promise<Comment> {
    const response = await addComment({
        path: { fundraiserId },
        body: comment,
        responseStyle: 'data',
        throwOnError: true,
    });

    return unwrapResult(response);
}
