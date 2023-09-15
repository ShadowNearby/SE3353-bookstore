export async function getOrderItemInCart() {
  return await fetch("http://localhost:8080/api/orderItem/cart", {
    method: "GET",
    credentials: "include",
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
