import { UpdateEstabelecimentoDto } from './update-estabelecimento.dto';
import { EstabelecimentoService } from './estabelecimento.service';
import { Body, Controller, Delete, Get, Param, Post, Put, Query } from '@nestjs/common';
import { CreateEstabelecimentoDto } from './create-estabelecimento.dto';

@Controller('estabelecimento')
export class EstabelecimentoController {

  constructor(private estabelecimentoService: EstabelecimentoService) { }

  @Get()
  async getEstabelecimentos() {
    return this.estabelecimentoService.getEstabelecimentos();
  }

  @Get(':estabelecimentoCnpj')
  async getEstabelecimento(@Param() estabelecimentoCnpj) {
    return this.estabelecimentoService.getEstabelecimento(estabelecimentoCnpj);
  }

  @Post()
  async addEstabelecimento(@Body() CreateEstabelecimentoDto: CreateEstabelecimentoDto) {
    return this.estabelecimentoService.addEstabelecimento(CreateEstabelecimentoDto)
  }

  @Put()
  async updateEstabelecimento(@Body() updateEstabelecimentoDto: UpdateEstabelecimentoDto) {
    return this.estabelecimentoService.updateEstabelecimento(updateEstabelecimentoDto)
  }

  @Delete(':estabelecimentoCnpj')
  async deleteEstabelecimento(@Param() estabelecimentoCnpj) {
    return this.estabelecimentoService.deleteEstabelecimento(estabelecimentoCnpj)
  }
}