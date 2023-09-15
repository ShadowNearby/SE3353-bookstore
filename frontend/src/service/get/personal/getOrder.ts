export async function getOrders() {
  return await fetch("http://localhost:8080/api/order", {
    method: "GET",
    credentials: "include",
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
