import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { EstabelecimentosService } from '../estabelecimentos.service';
import { CreateEstabelecimentoDto } from '../dto/create-estabelecimento.dto';
import { UpdateEstabelecimentoDto } from './../dto/update-estabelecimento.dto';

@Controller('estabelecimento')
export class EstabelecimentosController {
  constructor(private readonly estabelecimentosService: EstabelecimentosService) { }

  @Post()
  create(@Body() data: CreateEstabelecimentoDto) {
    return this.estabelecimentosService.create(data);
  }

  @Get()
  findAll() {
    return this.estabelecimentosService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.estabelecimentosService.findOne(+id);
  }

  @Patch(':cnpj')
  update(@Param('cnpj') cnpj: string, @Body() updateEstabelecimentoDto: UpdateEstabelecimentoDto) {
    return this.estabelecimentosService.update(cnpj, updateEstabelecimentoDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.estabelecimentosService.remove(+id);
  }
}
