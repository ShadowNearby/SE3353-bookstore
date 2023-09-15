export async function getUser() {
  return await fetch("http://localhost:8080/api/user", {
    method: "GET",
    credentials: "include",
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
