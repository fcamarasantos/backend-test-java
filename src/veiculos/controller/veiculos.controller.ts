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

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.veiculosService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateEstabelecimentoDto: UpdateVeiculoDto) {
    return this.veiculosService.update(+id, updateEstabelecimentoDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.veiculosService.remove(+id);
  }
}
