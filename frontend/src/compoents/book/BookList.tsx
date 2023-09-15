import React, { useEffect, useState } from "react";
import { Input, List, Spin } from "antd";
import { Book } from "./Book";
import { IBook } from "../../interface";
import { getBooks } from "../../service/get/getBook";

export const BookList = (): JSX.Element => {
  const [books, setBooks] = useState<IBook[]>([]);
  const [showedBooks, setShowedBooks] = useState<IBook[]>([]);
  useEffect(() => {
    getBooks().then((res) => {
      setBooks(res);
      setShowedBooks(res);
    });
  }, []);
  const [column, setColumn] = useState(window.innerWidth / 400);
  const handlePageResize = () => {
    setColumn(window.innerWidth / 400);
  };
  useEffect(() => {
    window.addEventListener("resize", () => handlePageResize());
    return window.removeEventListener("resize", () => handlePageResize());
  }, []);
  const searchByName = (target: string) => {
    let newList: IBook[] = [];
    books.forEach((value: IBook) => {
      if (value.name.match(target)) newList.push(value);
    });
    setShowedBooks(newList);
  };
  return (
    <div>
      <Input.Search
        placeholder={"搜索图书"}
        onSearch={(target) => {
          searchByName(target);
        }}
      ></Input.Search>
      <span style={{ marginTop: 10 }}>
        {showedBooks ? (
          <List
            grid={{
              gutter: column * 2,
              column: column,
            }}
            dataSource={showedBooks}
            pagination={{
              pageSize: column * 2,
            }}
            size={"small"}
            renderItem={(item: IBook) => (
              <List.Item>
                <Book book={item}></Book>
              </List.Item>
            )}
          ></List>
        ) : (
          <Spin size={"large"} />
        )}
      </span>
    </div>
  );
};
