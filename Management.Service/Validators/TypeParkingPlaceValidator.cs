using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class TypeParkingPlaceValidator: AbstractValidator<TypeParkingPlace>
    {
        public TypeParkingPlaceValidator()
        {
            RuleFor(tpp => tpp)
                .NotEmpty();

            RuleFor(tpp => tpp.description)
                .NotNull().WithMessage("Informe a descrição do tipo da vaga (Carro ou Moto)")
                .NotEmpty().WithMessage("Informe a descrição do tipo da vaga (Carro ou Moto)");
        }
    }
}
