using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using news.Models;
using news.Repositories;

namespace news.Controllers
{
    [Produces("application/json")]
    [Route("phonebook/v1/api/categories")]
    public class CategoriesController : Controller
    {
        private readonly CategoryContext _context;

        public CategoriesController(CategoryContext context)
        {
            _context = context;
        }

        // GET: phonebook/v1/api/categories
        [HttpGet]
        public IEnumerable<Category> GetCategory()
        {
            return _context.Category;
        }

        // GET: phonebook/v1/api/categories/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetCategory([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var category = await _context.Category.SingleOrDefaultAsync(m => m.Id == id);

            if (category == null)
            {
                return NotFound();
            }

            return Ok(category);
        }

        // PUT: phonebook/v1/api/categories/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCategory([FromRoute] long id, [FromBody] Category category)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != category.Id)
            {
                return BadRequest();
            }

            _context.Entry(category).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CategoryExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: phonebook/v1/api/categories
        [HttpPost]
        public async Task<IActionResult> PostCategory([FromBody] Category category)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Category.Add(category);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCategory", new { id = category.Id }, category);
        }

        // DELETE: phonebook/v1/api/categories/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCategory([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var category = await _context.Category.SingleOrDefaultAsync(m => m.Id == id);
            if (category == null)
            {
                return NotFound();
            }

            _context.Category.Remove(category);
            await _context.SaveChangesAsync();

            return Ok(category);
        }

        private bool CategoryExists(long id)
        {
            return _context.Category.Any(e => e.Id == id);
        }
    }
}