import { Injectable, NotFoundException } from '@nestjs/common';

export interface Memo {
  id: number;
  content: string;
}

@Injectable()
export class AppService {
  private memos = new Map<number, Memo>();
  private lastId: number = 0;

  getMemos(): Memo[] {
    return [...this.memos.values()];
  }

  getMemoById(id: number): Memo {
    const memo = this.memos.get(id);
    if (!memo) {
      throw new NotFoundException(`Memo with id ${id} not found`);
    }
    return memo;
  }

  createMemo(content: string): Memo {
    const newMemo: Memo = { id: ++this.lastId, content };
    this.memos.set(newMemo.id, newMemo);
    return newMemo;
  }

  updateMemo(id: number, content: string): Memo {
    const memo = this.getMemoById(id);
    memo.content = content;
    return memo;
  }

  deleteMemo(id: number): { message: string; id: number } {
    if (!this.memos.delete(id)) {
      throw new NotFoundException(`Memo with id ${id} not found`);
    }
    return { message: 'delete', id };
  }
}
