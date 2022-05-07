import { ApiOkResponse, ApiCreatedResponse } from '@nestjs/swagger';
import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { VeiculosService } from '../veiculos.service';
import { CreateVeiculoDto } from '../dto/create-veiculo.dto';
import { UpdateVeiculoDto } from '../dto/update-veiculo.dto';

@Controller('veiculo')
export class VeiculosController {
  constructor(private readonly veiculosService: VeiculosService) { }

  @Post()
  @ApiCreatedResponse({ description: 'Veículo cadastrado com sucesso.' })
  create(@Body() data: CreateVeiculoDto) {
    return this.veiculosService.create(data);
  }

  @Get()
  @ApiOkResponse({ description: 'Lista todos os veículos cadastrados.' })
  findAll() {
    return this.veiculosService.findAll();
  }

  @Get(':placa')
  @ApiOkResponse({ description: 'Lista veículo pela PLACA.' })
  findOne(@Param('placa') placa: string) {
    return this.veiculosService.findOne(placa);
  }

  @Patch(':placa')
  @ApiOkResponse({ description: 'Atualiza veículo pela PLACA.' })
  update(@Param('placa') placa: string, @Body() updateEstabelecimentoDto: UpdateVeiculoDto) {
    return this.veiculosService.update(placa, updateEstabelecimentoDto);
  }

  @Delete(':placa')
  @ApiOkResponse({ description: 'Deleta veículo pela PLACA.' })
  remove(@Param('placa') placa: string) {
    return this.veiculosService.remove(placa);
  }
}
