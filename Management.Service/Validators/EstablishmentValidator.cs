using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class EstablishmentValidator: AbstractValidator<Establishment>
    {
        public EstablishmentValidator()
        {
            RuleFor(e => e)
                .NotNull();

            RuleFor(e => e.address)
                .NotNull().WithMessage("Informe o endereço do estabelecimento")
                .NotEmpty().WithMessage("Informe o endereço do estabelecimento")
                .MaximumLength(100).WithMessage("Endereço deve conter no maximo 100 digitos");

            RuleFor(e => e.fone)
                .NotNull().WithMessage("Informe o telefone do estabelecimento")
                .NotEmpty().WithMessage("Informe o telefone do estabelecimento")
                .MaximumLength(15).WithMessage("Telefone deve conter no maximo 15 digitos");

            RuleFor(e => e.name)
                .NotNull().WithMessage("Nome do estabelecimento deve conter no maximo 60 digitos")
                .NotEmpty().WithMessage("Nome do estabelecimento deve conter no maximo 60 digitos")
                .MaximumLength(60).WithMessage("Nome do estabelecimento deve conter no maximo 60 digitos");

            RuleFor(e => e.qt_parking_place_car)
                .NotNull()
                .WithMessage("Informe a quantidade de vagas para carro no estabelecimento");

            RuleFor(e => e.qt_parking_place_motorcycle)
                .NotNull()
                .WithMessage("Informe a quantidade de vagas para moto no estabelecimento");
        }
    }
}
