import { getRequest, microServiceUrl } from "../../utility/request";

interface BookAuthor {
  author: string;
}

export async function getAuthorByName(name: string): Promise<BookAuthor> {
  const url = `${microServiceUrl}book?name=${name}`;
  const response = await getRequest({ url: url });
  if (response.ok) return await response.json();
  console.error(response.body);
  throw Error;
}
