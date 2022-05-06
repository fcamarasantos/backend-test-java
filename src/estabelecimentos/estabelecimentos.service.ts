import { Veiculo } from 'src/veiculos/entities/veiculo.entity';
import { Injectable, Inject } from '@nestjs/common';
import { Repository } from 'typeorm';
import { CreateEstabelecimentoDto } from './dto/create-estabelecimento.dto';
import { resultEstabelecimentoDto } from './dto/result-estabelecimento.dto';
import { UpdateEstabelecimentoDto } from './dto/update-estabelecimento.dto';
import { Estabelecimento } from './entities/estabelecimento.entity';

@Injectable()
export class EstabelecimentosService {

  constructor(
    @Inject('ESTABELECIMENTO_REPOSITORY')
    private estabelecimentoRepository: Repository<Estabelecimento>,
  ) { }

  async create(data: CreateEstabelecimentoDto): Promise<resultEstabelecimentoDto> {
    return this.estabelecimentoRepository.save(data)
      .then((result) => {
        return <resultEstabelecimentoDto>{
          status: true,
          mensagem: "Estabelecimento cadastrado"
        };
      }).catch((error) => {
        return <resultEstabelecimentoDto>{
          status: false,
          mensagem: `Erro ao tentar cadastrar estabelecimento. ${error}`
        };
      })
  }

  findAll(): Promise<Estabelecimento[]> {
    return this.estabelecimentoRepository.find();
  }

  async findOne(cnpj: number): Promise<Estabelecimento | resultEstabelecimentoDto> {
    const estabelecimento = await this.estabelecimentoRepository.findOne(cnpj)
    if (!estabelecimento) {
      return <resultEstabelecimentoDto>{
        status: false,
        mensagem: `Estabelecimento não encontrado.`
      }
    } else {
      return this.estabelecimentoRepository.findOne(cnpj);
    }
  }

  async remove(cnpj: number): Promise<resultEstabelecimentoDto> {
    const estabelecimentoRemove = await this.estabelecimentoRepository.findOne(cnpj)
    if (!estabelecimentoRemove) {
      return <resultEstabelecimentoDto>{
        status: false,
        mensagem: `Estabelecimento não encontrado.`
      }
    } else {
      return this.estabelecimentoRepository.delete(cnpj)
        .then((result) => {
          return <resultEstabelecimentoDto>{
            status: true,
            mensagem: "Estabelecimento deletado"
          };
        }).catch((error) => {
          return <resultEstabelecimentoDto>{
            status: false,
            mensagem: `Erro ao tentar deletar estabelecimento. ${error}`
          };
        })
    }
  }

  async update(cnpj: string, data: UpdateEstabelecimentoDto) {
    const estabelecimentoUpdate = await this.estabelecimentoRepository.findOne(cnpj)
    if (!estabelecimentoUpdate) {
      return <resultEstabelecimentoDto>{
        status: false,
        mensagem: `Estabelecimento não encontrado.`
      }
    } else {
      estabelecimentoUpdate.cnpj = cnpj
      estabelecimentoUpdate.nome = data.nome
      estabelecimentoUpdate.endereco = data.endereco
      estabelecimentoUpdate.telefone_estabelecimento = data.telefone_estabelecimento
      estabelecimentoUpdate.total_vagas_carros = data.total_vagas_carros
      estabelecimentoUpdate.total_vagas_motos = data.total_vagas_motos
      return this.estabelecimentoRepository.save(estabelecimentoUpdate)
        .then((result) => {
          return <resultEstabelecimentoDto>{
            status: true,
            mensagem: "Estabelecimento atualizado"
          };
        }).catch((error) => {
          return <resultEstabelecimentoDto>{
            status: false,
            mensagem: `Erro ao tentar cadastrar estabelecimento. ${error}`
          };
        })
    }
  }
}
