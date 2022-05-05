import { PartialType } from '@nestjs/mapped-types';
import { CreateEstabelecimentoDto } from './create-estabelecimento.dto';

export class UpdateEstabelecimentoDto extends PartialType(CreateEstabelecimentoDto) { }
