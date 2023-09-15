export async function getAllUsers() {
  return await fetch("http://localhost:8080/admin/users", {
    method: "GET",
    credentials: "include",
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
