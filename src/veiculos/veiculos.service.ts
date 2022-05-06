import { Injectable, Inject } from '@nestjs/common';
import { Repository } from 'typeorm';
import { CreateVeiculoDto } from './dto/create-veiculo.dto';
import { resultVeiculoDto } from './dto/result-veiculo.dto';
import { UpdateVeiculoDto } from './dto/update-veiculo.dto';
import { Veiculo } from './entities/veiculo.entity';

@Injectable()
export class VeiculosService {

  constructor(
    @Inject('VEICULO_REPOSITORY')
    private veiculoRepository: Repository<Veiculo>,
  ) { }

  async create(data: CreateVeiculoDto): Promise<resultVeiculoDto> {

    return this.veiculoRepository.save(data)
      .then((result) => {
        return <resultVeiculoDto>{
          status: true,
          mensagem: "Veiculo cadastrado"
        };
      }).catch((error) => {
        return <resultVeiculoDto>{
          status: false,
          mensagem: `Erro ao tentar cadastrar Veiculo. ${error}`
        };
      })
  }

  findAll(): Promise<Veiculo[]> {
    return this.veiculoRepository.find();
  }

  findOne(id: number): Promise<Veiculo> {
    return this.veiculoRepository.findOne(id);
  }

  async remove(id: number): Promise<resultVeiculoDto> {
    return this.veiculoRepository.delete(id)
      .then((result) => {
        return <resultVeiculoDto>{
          status: true,
          mensagem: "Veiculo deletado"
        };
      }).catch((error) => {
        return <resultVeiculoDto>{
          status: false,
          mensagem: `Erro ao tentar deletar veiculo. ${error}`
        };
      })
  }

  update(id: number, data: UpdateVeiculoDto) {
    return this.veiculoRepository.save(data)
      .then((result) => {
        return <resultVeiculoDto>{
          status: true,
          mensagem: "Veiculo atualizado"
        };
      }).catch((error) => {
        return <resultVeiculoDto>{
          status: false,
          mensagem: `Erro ao tentar cadastrar veiculo. ${error}`
        };
      })
  }
}