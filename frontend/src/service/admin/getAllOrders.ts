export async function getAllOrders() {
  return await fetch("http://localhost:8080/admin/orders", {
    method: "GET",
    credentials: "include",
  })
    .then((res) => res.json())
    .catch((err) => console.log(err));
}
