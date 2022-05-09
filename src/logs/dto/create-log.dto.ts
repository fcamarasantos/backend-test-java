import { CreateEstabelecimentoDto } from './../../estabelecimentos/dto/create-estabelecimento.dto';
import { Veiculo } from 'src/veiculos/entities/veiculo.entity';
import { Estabelecimento } from 'src/estabelecimentos/entities/estabelecimento.entity';
import { CreateVeiculoDto } from 'src/veiculos/dto/create-veiculo.dto';
import { ApiProperty } from '@nestjs/swagger';

type tipo_evento = 'entrada' | 'saida'

export class CreateLogDto {
  @ApiProperty({ type: Number, description: 'Identificador do Log.' })
  readonly id: number;
  @ApiProperty({ type: String, description: 'Tipo do evento [entrada] ou [saida].' })
  readonly tipo_evento: tipo_evento;
  @ApiProperty({ type: CreateVeiculoDto, description: 'Veiculo identificado para o Log.' })
  readonly veiculo: Veiculo;
  @ApiProperty({ type: CreateEstabelecimentoDto, description: 'Estabelecimento do Log.' })
  readonly estabelecimento: Estabelecimento;
}