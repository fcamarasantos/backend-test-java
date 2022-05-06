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

  async findOne(placa: string): Promise<Veiculo | resultVeiculoDto> {
    const veiculo = await this.veiculoRepository.findOne(placa)
    if (!veiculo) {
      return <resultVeiculoDto>{
        status: false,
        mensagem: `Veículo não encontrado.`
      }
    } else {
      return this.veiculoRepository.findOne(placa);
    }
  }

  async remove(placa: string): Promise<resultVeiculoDto> {
    const veiculoRemove = await this.veiculoRepository.findOne(placa)
    if (!veiculoRemove) {
      return <resultVeiculoDto>{
        status: false,
        mensagem: `Veículo não encontrado.`
      }
    } else {
      return this.veiculoRepository.delete(placa)
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
  }

  async update(placa: string, data: UpdateVeiculoDto) {
    const veiculoUpdate = await this.veiculoRepository.findOne(placa)
    if (!veiculoUpdate) {
      return <resultVeiculoDto>{
        status: false,
        mensagem: `Veículo não encontrado.`
      }
    } else {
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
}