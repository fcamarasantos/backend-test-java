import { UpdateVeiculoDto } from './update-veiculo.dto';
import { VeiculoService } from './veiculo.service';
import { Body, Controller, Delete, Get, Param, Post, Put, Query } from '@nestjs/common';
import { CreateVeiculoDto } from './create-veiculo.dto';

@Controller('veiculo')
export class VeiculoController {

  constructor(private veiculoService: VeiculoService) { }

  @Get()
  async getVeiculos() {
    return this.veiculoService.getVeiculos();
  }

  @Get(':veiculoId')
  async getVeiculo(@Param() veiculoId) {
    return this.veiculoService.getVeiculo(veiculoId);
  }

  @Post()
  async addVeiculo(@Body() CreateVeiculoDto: CreateVeiculoDto) {
    return this.veiculoService.addVeiculo(CreateVeiculoDto)
  }

  @Put()
  async updateVeiculo(@Body() updateVeiculoDto: UpdateVeiculoDto) {
    return this.veiculoService.updateVeiculo(updateVeiculoDto)
  }

  @Delete(':veiculoId')
  async deleteVeiculo(@Param() veiculoId) {
    return this.veiculoService.deleteVeiculo(veiculoId)
  }
}