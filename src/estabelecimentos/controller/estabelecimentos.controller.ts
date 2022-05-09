import { Controller, Get, Post, Body, Patch, Param, Delete, Request, Response } from '@nestjs/common';
import { EstabelecimentosService } from '../estabelecimentos.service';
import { CreateEstabelecimentoDto } from '../dto/create-estabelecimento.dto';
import { UpdateEstabelecimentoDto } from './../dto/update-estabelecimento.dto';
import { ApiOkResponse, ApiCreatedResponse } from '@nestjs/swagger';

@Controller('estabelecimento')
export class EstabelecimentosController {
  constructor(private readonly estabelecimentosService: EstabelecimentosService) { }

  @Post()
  @ApiCreatedResponse({ description: 'Estabelecimento cadastrado com sucesso.' })
  create(@Body() data: CreateEstabelecimentoDto) {
    return this.estabelecimentosService.create(data);
  }

  @Get()
  @ApiOkResponse({ description: 'Lista todos os estabelecimentos cadastrados.' })
  findAll() {
    return this.estabelecimentosService.findAll();
  }


  @Get(':cnpj')
  @ApiOkResponse({ description: 'Lista estabelecimento pelo CNPJ.' })
  findOne(@Param('cnpj') cnpj: number) {
    return this.estabelecimentosService.findOne(cnpj);
  }

  @Patch(':cnpj')
  @ApiOkResponse({ description: 'Atualiza estabelecimento pelo CNPJ.' })
  update(@Param('cnpj') cnpj: string, @Body() updateEstabelecimentoDto: UpdateEstabelecimentoDto) {
    return this.estabelecimentosService.update(cnpj, updateEstabelecimentoDto);
  }

  @Delete(':cnpj')
  @ApiOkResponse({ description: 'Deleta estabelecimento pelo CNPJ.' })
  remove(@Param('cnpj') cnpj: number) {
    return this.estabelecimentosService.remove(cnpj);
  }
}
