import axios from "axios";

const moveInstance = axios.create({
  baseURL: "http://shchauha02-mac.local:8080",
});

export default moveInstance;
