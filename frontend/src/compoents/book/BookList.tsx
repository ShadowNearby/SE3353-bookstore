import React, { useEffect, useState } from "react";
import { Input, List, Spin } from "antd";
import { Book } from "./Book";
import { IBook } from "../../interface";
import { getBooks, getBooksByType } from "../../service/get/getBook";
import { getBookByName } from "../../service/get/getBookByName";

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
  const searchByName = async (target: string) => {
    if (target === "") {
      const result = await getBooks();
      setBooks(result);
      setShowedBooks(result);
      return;
    }
    const result = await getBookByName(target);
    setShowedBooks(result);
  };
  const searchByType = async (target: string) => {
    if (target === "") {
      const books = await getBooks();
      setBooks(books);
      setShowedBooks(books);
      return;
    }
    const books = await getBooksByType(target);
    setBooks(books);
    setShowedBooks(books);
  };
  return (
    <div>
      <Input.Search
        placeholder={"搜索图书"}
        onSearch={(target) => {
          searchByName(target);
        }}
      ></Input.Search>
      <Input.Search
        placeholder={`按类型搜索`}
        onSearch={(target) => searchByType(target)}
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
