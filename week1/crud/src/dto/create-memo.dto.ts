import { IsString, Matches } from 'class-validator';

// DTO for post and update. since both of them only get content
export class CreateMemoDto {
  @IsString({ message: 'content must be string.' })
  @Matches(/\S/, { message: 'content must includes word.' })
  content: string;
}
