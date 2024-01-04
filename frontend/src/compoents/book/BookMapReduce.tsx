import { Button } from "antd";
import { useState } from "react";
import { DoMapReduce } from "../../service/mr/DoMapReduce";

export const BookMapReduce = () => {
  const [result, setResult] = useState("");
  return (
    <div>
      <Button
        onClick={async () => {
          const data = await DoMapReduce();
          setResult(data);
        }}
      >
        do map reduce
      </Button>
      <span>{`结果: ${result}`}</span>
    </div>
  );
};
