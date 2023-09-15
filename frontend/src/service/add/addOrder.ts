export async function addOrder(orderItemIds: number[]) {
  await fetch("http://localhost:8080/api/order/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({
      orderItemIds: orderItemIds,
    }),
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
