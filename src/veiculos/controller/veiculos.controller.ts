import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { VeiculosService } from '../veiculos.service';
import { CreateVeiculoDto } from '../dto/create-veiculo.dto';
import { UpdateVeiculoDto } from '../dto/update-veiculo.dto';

@Controller('veiculo')
export class VeiculosController {
  constructor(private readonly veiculosService: VeiculosService) { }

  @Post()
  create(@Body() data: CreateVeiculoDto) {
    return this.veiculosService.create(data);
  }

  @Get()
  findAll() {
    return this.veiculosService.findAll();
  }

  @Get(':placa')
  findOne(@Param('placa') placa: string) {
    return this.veiculosService.findOne(placa);
  }

  @Patch(':placa')
  update(@Param('placa') placa: string, @Body() updateEstabelecimentoDto: UpdateVeiculoDto) {
    return this.veiculosService.update(placa, updateEstabelecimentoDto);
  }

  @Delete(':placa')
  remove(@Param('placa') placa: string) {
    return this.veiculosService.remove(placa);
  }
}
