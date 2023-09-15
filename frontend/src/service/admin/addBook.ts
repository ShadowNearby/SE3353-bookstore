interface Props {
  name: string;
  description: string;
  author: string;
  image: string;
  price: number;
  inventory: number;
  isbn: string;
}

export async function addBook({
  name,
  description,
  author,
  image,
  price,
  inventory,
  isbn,
}: Props) {
  return fetch("http://localhost:8080/admin/book/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      name: name,
      description: description,
      author: author,
      image: image,
      price: price,
      inventory: inventory,
      isbn: isbn,
    }),
    credentials: "include",
  }).catch((err) => alert(err));
}
