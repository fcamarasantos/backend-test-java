import { ApiCreatedResponse, ApiOkResponse } from '@nestjs/swagger';
import { Controller, Get, Post, Body, Patch, Param, Delete, Query } from '@nestjs/common';
import { LogsService } from '../logs.service';
import { CreateLogDto } from '../dto/create-log.dto';

@Controller('log')
export class LogsController {
  constructor(private readonly logsService: LogsService) { }

  @Get()
  @ApiOkResponse({ description: 'Lista entradas e saídas de veículos nos estabelecimentos.' })
  findAll() {
    return this.logsService.findAll();
  }

  @Post()
  @ApiCreatedResponse({ description: 'Registra entrada ou saída de um veículo num estabelecimento.' })
  create(
    @Query('tipo_evento') tipo_evento: 'entrada' | 'saida',
    @Query('estabelecimento_cnpj') estabelecimento_cnpj: number,
    @Query('veiculo_placa') veiculo_placa: string,
    @Body() body: CreateLogDto) {
    return this.logsService.create(tipo_evento, estabelecimento_cnpj, veiculo_placa, body);
  }
}
