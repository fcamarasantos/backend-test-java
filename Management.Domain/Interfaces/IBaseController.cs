using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Text;

namespace Management.Domain.Interfaces
{
    public interface IBaseController
    {
        IActionResult get(int? id = null);
        IActionResult post<T>(T model);
        IActionResult put<T>(int id, T model);
        IActionResult remove<T>(int id);
    }
}
