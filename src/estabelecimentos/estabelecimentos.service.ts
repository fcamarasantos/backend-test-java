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
    const estabelecimento = new Estabelecimento()
    estabelecimento.cnpj = Number(data.cnpj)
    estabelecimento.nome = data.nome
    estabelecimento.endereco = data.endereco
    estabelecimento.endereco_id = Number(data.endereco_id)
    estabelecimento.telefone_estabelecimento = data.telefone_estabelecimento
    estabelecimento.total_vagas_carros = data.total_vagas_carros
    estabelecimento.total_vagas_motos = data.total_vagas_motos
    return this.estabelecimentoRepository.save(estabelecimento)
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

  findOne(cnpj: number): Promise<Estabelecimento> {
    return this.estabelecimentoRepository.findOne(cnpj);
  }

  async remove(cnpj: number): Promise<void> {
    await this.estabelecimentoRepository.delete(cnpj);
  }

  update(id: number, updateEstabelecimentoDto: UpdateEstabelecimentoDto) {
    return `This action updates a #${id} estabelecimento`;
  }
}
