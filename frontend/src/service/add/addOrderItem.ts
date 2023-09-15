export async function addOrderItem(bookId: number, count: number) {
  return await fetch("http://localhost:8080/api/orderItem/cart/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({
      bookId: bookId,
      count: count,
    }),
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
