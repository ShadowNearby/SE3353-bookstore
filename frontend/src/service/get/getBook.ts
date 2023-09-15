import { IBook } from "../../interface";

export async function getBooks(): Promise<IBook[]> {
  const response = await fetch("http://localhost:8080/api/books", {
    method: "GET",
    credentials: "include",
  });
  return response.json();
}

export async function getBookById(id: number): Promise<IBook> {
  return await fetch("http://localhost:8080/api/book/" + id.toString(), {
    method: "GET",
    credentials: "include",
  })
    .then((res) => res.json())
    .catch((err) => console.log(err));
}
