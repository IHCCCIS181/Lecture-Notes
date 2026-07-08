export default async function handler(req, res) {
    if (req.method !== 'POST') {
        res.setHeader('Allow', ['POST']);
        return res.status(405).send('Method Not Allowed');
    }

    try {
        const backendResponse = await fetch('http://localhost:8080/api/users/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(req.body),
        });

        const text = await backendResponse.text();
        return res.status(backendResponse.status).send(text);
    } catch (error) {
        console.error('Proxy registration failed:', error);
        return res.status(500).send('Unable to reach backend');
    }
}