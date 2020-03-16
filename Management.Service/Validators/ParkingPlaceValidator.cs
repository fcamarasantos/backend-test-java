using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class ParkingPlaceValidator: AbstractValidator<ParkingPlace>
    {
        public ParkingPlaceValidator()
        {
            RuleFor(pp => pp)
                .NotNull();

            RuleFor(pp => pp.date_in)
                .Null();

            RuleFor(pp => pp.date_out)
                .Null();

            RuleFor(pp => pp.description)
                .NotNull().WithMessage("Informe a descrição da vaga (coluna +linha)")
                .NotEmpty().WithMessage("Informe a descrição da vaga (coluna +linha)")
                .MaximumLength(30).WithMessage("Descrição da vaga deve ter até 30 digitos.");

            RuleFor(pp => pp.id_establishment)
                .NotNull().WithMessage("Informe o id do estabelecimento");

            RuleFor(pp => pp.id_column)
                .NotNull().WithMessage("Informe a coluna da vaga."); 

            RuleFor(pp => pp.id_line)
                .NotNull().WithMessage("Informe a linha da vaga.");

            RuleFor(pp => pp.id_vehicle)
                .NotNull().WithMessage("Informe o id do veiculo.");
        }
    }
}
