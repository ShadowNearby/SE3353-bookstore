import React from "react";
import { Link } from "react-router-dom";
import { Card, Image } from "antd";
import { IBook } from "../../interface";

interface Props {
  book: IBook;
}

const { Meta } = Card;
export const Book = ({ book }: Props): JSX.Element => {
  return (
    <Card hoverable style={{}}>
      <Link to={`/book/${book.id}`} state={{ id: book.id }}>
        <Image src={book.image} alt={"error"} preview={false} />
        <Meta
          title={
            <div>
              <div style={{ fontSize: 25 }}>{book.name}</div>
              <div
                style={{ fontSize: 20, color: "red", fontWeight: "lighter" }}
              >
                {"￥"}
                {book.price}
              </div>
            </div>
          }
          style={{ paddingTop: 5 }}
        />
      </Link>
    </Card>
  );
};
