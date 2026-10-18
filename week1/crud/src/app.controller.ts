import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  ParseIntPipe,
  Post,
  Put,
} from '@nestjs/common';
import { AppService } from './app.service.js';
import type { Memo } from './app.service.js'; 
import { CreateMemoDto } from './dto/create-memo.dto.js';

@Controller('memos')
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getMemos(): Memo[] {
    return this.appService.getMemos();
  }

  @Get(':id')
  getMemoById(@Param('id', ParseIntPipe) id: number): Memo { //param(id) returns string -> parseIntPipe guarantees(transform into number) number type
    return this.appService.getMemoById(id);
  }

  @Post()
  createMemo(@Body() dto: CreateMemoDto): Memo {
    return this.appService.createMemo(dto.content);
  }

  @Put(':id')
  updateMemo(
    @Param('id', ParseIntPipe) id: number,
    @Body() dto: CreateMemoDto,
  ): Memo {
    return this.appService.updateMemo(id, dto.content);
  }

  @Delete(':id')
  deleteMemo(@Param('id', ParseIntPipe) id: number) {
    return this.appService.deleteMemo(id);
  }
}
