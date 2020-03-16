using FluentValidation;
using Management.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Service.Validators
{
    public class UserValidator: AbstractValidator<User>
    {
        public UserValidator()
        {
            RuleFor(u => u)
                .NotNull()
                .OnAnyFailure(u =>
                {
                    throw new Exception("User invalid");;
                });
        }
    }
}
