interface Props {
  id: number;
  name: string;
  description: string;
  author: string;
  image: string;
  price: number;
  inventory: number;
  isbn: string;
}

export async function putBook({
  id,
  name,
  description,
  author,
  image,
  price,
  inventory,
  isbn,
}: Props) {
  return await fetch("http://localhost:8080/admin/book/put", {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify({
      id: id,
      name: name,
      description: description,
      author: author,
      image: image,
      price: price,
      inventory: inventory,
      isbn: isbn,
    }),
  }).catch((err) => alert(err));
}
