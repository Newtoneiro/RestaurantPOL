import axios from 'axios';

const publicFetch = axios.create({
    baseURL: "http://localhost:8080/api/v1"
});

export { publicFetch };
