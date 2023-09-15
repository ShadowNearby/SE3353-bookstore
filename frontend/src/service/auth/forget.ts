export async function handleForget(
  username: string,
  newPassword: string,
  email: string
) {
  return await fetch("http://localhost:8080/forget", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({
      username: username,
      password: newPassword,
      email: email,
    }),
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
