export async function deleteOrderItem(orderItemId: number) {
  return await fetch("http://localhost:8080/api/orderItem/cart/remove", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({
      id: orderItemId,
    }),
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
