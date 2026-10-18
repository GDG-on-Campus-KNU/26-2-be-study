import { createInterface } from "node:readline/promises";

const rl = createInterface({ input: process.stdin, output: process.stdout });

const items = [];
let nextId = 1;

async function create() {
  const name = await rl.question("생성: ");
  const item = { id: nextId++, name };
  items.push(item);
  console.log("생성완료:", item);
}

function read() {
  if (items.length === 0) {
    console.log("데이터없음. ");
    return;
  }
  console.log(items); // console.table also works. -> structured view
}

async function update() {
  const id = Number(await rl.question("수정 id: "));
  const item = items.find((it) => it.id === id);
  if (!item) {
    console.log(`id ${id} 없음`);
    return;
  }
  item.name = await rl.question("새 이름: ");
  console.log("수정 완료:", item);
}

async function remove() {
  const id = Number(await rl.question("삭제할 id: "));
  const index = items.findIndex((it) => it.id === id);
  if (index === -1) {
    console.log(`id ${id} 없음`);
    return;
  }
  const [deleted] = items.splice(index, 1);
  console.log("삭제 완료:", deleted);
  
 
}

async function main() {
  while (true) {
    console.log("\n1.생성  2.조회  3.수정  4.삭제  0.종료");
    const choice = (await rl.question("선택: ")).trim();

    if (choice === "1") await create();
    else if (choice === "2") read();
    else if (choice === "3") await update();
    else if (choice === "4") await remove();
    else if (choice === "0") break;
    else console.log("잘못된 입력 -> retry! ");
  }

  rl.close();
  console.log("종료");
}

main();


