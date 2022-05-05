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

    const veiculo = new Veiculo()
    veiculo.placa = data.placa
    veiculo.tipo = data.tipo
    veiculo.marca = data.marca
    veiculo.modelo = data.modelo
    veiculo.cor = data.cor
    return this.veiculoRepository.save(veiculo)
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

  findOne(cnpj: number): Promise<Veiculo> {
    return this.veiculoRepository.findOne(cnpj);
  }

  async remove(cnpj: number): Promise<resultVeiculoDto> {
    return this.veiculoRepository.delete(cnpj)
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

  update(id: number, updateVeiculoDto: UpdateVeiculoDto) {
    return `This action updates a #${id} veiculo`;
  }
}
