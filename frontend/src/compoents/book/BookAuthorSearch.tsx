import { Input } from "antd";
import { useState } from "react";
import { getAuthorByName } from "../../service/get/getAuthorByName";

export const BookAuthorSearch = () => {
  const [author, setAuthor] = useState<string>("");
  return (
    <div>
      <Input.Search
        placeholder={"搜索作者"}
        onSearch={async (value) => {
          const data = await getAuthorByName(value);
          setAuthor(data.author);
        }}
      ></Input.Search>
      <span>{`搜索结果: ${author}`}</span>
    </div>
  );
};
