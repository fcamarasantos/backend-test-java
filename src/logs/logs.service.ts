import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import { Veiculo } from 'src/veiculos/entities/veiculo.entity';
import { Injectable, Inject } from '@nestjs/common';
import { Repository, getRepository, createQueryBuilder } from 'typeorm';
import { CreateLogDto } from './dto/create-log.dto';
import { resultLogDto } from './dto/result-log.dto';
import { UpdateLogDto } from './dto/update-log.dto';
import { Log } from './entities/log.entity';

type tipo_evento = 'entrada' | 'saida'

@Injectable()
export class LogsService {
  constructor(
    @Inject('LOG_REPOSITORY')
    private logRepository: Repository<Log>
  ) { }

  async create(tipo_evento: tipo_evento, estabelecimento_cnpj: number, veiculo_placa: string, data: CreateLogDto): Promise<resultLogDto> {

    // tratar redundancias aqui

    const estabelecimentoRepository = getRepository(Estabelecimento);
    const veiculoRepository = getRepository(Veiculo);

    const log = new Log()
    log.date = new Date()
    log.tipo_evento = tipo_evento
    log.estabelecimento = await estabelecimentoRepository.findOne(estabelecimento_cnpj)
    log.veiculo = await veiculoRepository.findOne(veiculo_placa)

    console.log(log)
    return this.logRepository.save(log)
      .then((result) => {
        return <resultLogDto>{
          status: true,
          mensagem: "Log cadastrado"
        };
      }).catch((error) => {
        return <resultLogDto>{
          status: false,
          mensagem: `Erro ao tentar cadastrar Log. ${error}`
        };
      })
  }

  findAll(): Promise<Log[]> {
    return this.logRepository.find();
  }

  findOne(id: number): Promise<Log> {
    return this.logRepository.findOne(id);
  }

  async remove(id: number): Promise<resultLogDto> {
    return this.logRepository.delete(id)
      .then((result) => {
        return <resultLogDto>{
          status: true,
          mensagem: "log deletado"
        };
      }).catch((error) => {
        return <resultLogDto>{
          status: false,
          mensagem: `Erro ao tentar deletar Log. ${error}`
        };
      })
  }

  update(id: number, data: UpdateLogDto) {
    return this.logRepository.save(data)
      .then((result) => {
        return <resultLogDto>{
          status: true,
          mensagem: "Log atualizado"
        };
      }).catch((error) => {
        return <resultLogDto>{
          status: false,
          mensagem: `Erro ao tentar cadastrar Log. ${error}`
        };
      })
  }
}