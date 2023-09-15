interface Props {
  id: number;
}

export async function deleteBook({ id }: Props) {
  return fetch("http://localhost:8080/admin/book/delete", {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id: id,
    }),
    credentials: "include",
  }).catch((err) => alert(err));
}
