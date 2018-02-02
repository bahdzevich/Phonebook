using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using news.Models;
using news.Repositories;
using news.Dto;

namespace news.Controllers
{
    [Produces("application/json")]
    [Route("phonebook/news-service/api/news")]
    public class NewsController : Controller
    {
        private readonly NewsContext _context;

        public NewsController(NewsContext context)
        {
            _context = context;
        }

        // GET: phonebook/api/news
        [HttpGet]
        public IEnumerable<News> GetNews()
        {
            return _context.News;
        }

        // GET: phonebook/api/news/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetNews([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var news = await _context.News.SingleOrDefaultAsync(m => m.Id == id);

            if (news == null)
            {
                return NotFound();
            }

            return Ok(news);
        }

        // GET phonebook/api/news/categories/5
        [HttpGet("categories/{id}")]
        public  IActionResult GetNewsByCategory([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (!CategoryExists(id))
            {
                return NotFound();
            }

            var news = _context.News
                .Join(
                    _context.NewsCategory,
                    news1 => news1.Id,
                    newsCategory1 => newsCategory1.News.Id,
                    (emp1, newsCategory1) => new { emp1, newsCategory1 })
                .Join(
                    _context.Category,
                    anon1 => anon1.newsCategory1.Category.Id,
                    category1 => category1.Id,
                    (emp2, category1) => new { emp2, category1 })
                .Where(anon2 => anon2.category1.Id == id)
                .Select(anon2 => new {
                    Id = anon2.emp2.emp1.Id,
                    Title = anon2.emp2.emp1.Title,
                    Content = anon2.emp2.emp1.Content,
                    Created = anon2.emp2.emp1.Created,
                    UserId = anon2.emp2.emp1.UserId
                }).ToList();
                

            if (news != null)
            {
                return Ok(news);
            }
            else
            {
                return NotFound();
            }
        }

        // PUT: phonebook/api/news/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutNews([FromRoute] long id, [FromBody] News news)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != news.Id)
            {
                return BadRequest();
            }

            _context.Entry(news).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NewsExists(id))
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

        // POST: phonebook/api/news
        [HttpPost]
        public async Task<IActionResult> PostNews([FromBody] NewsRequestDto newsDto)
        {
            if (!ModelState.IsValid || newsDto.Categories == null)
            {
                return BadRequest(ModelState);
            }

            List<NewsCategory> newsCategoryList = new List<NewsCategory>();
            foreach (var categoryDto in newsDto.Categories)
            {
                if (!CategoryExists(categoryDto.Id))
                {
                    return BadRequest();
                }
                newsCategoryList.Add(new NewsCategory { CategoryId = categoryDto.Id });
            }

            var news = new News
            {
                Title = newsDto.Title,
                Content = newsDto.Content,
                UserId = newsDto.UserId
            };

            _context.News.Add(news);

            foreach (var newsCategory in newsCategoryList)
            {
                newsCategory.NewsId = news.Id;
                _context.NewsCategory.Add(newsCategory);
            }

            await _context.SaveChangesAsync();

            return CreatedAtAction("GetNews", new { id = news.Id }, news);
        }

        // DELETE: phonebook/api/news/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNews([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var news = await _context.News.SingleOrDefaultAsync(m => m.Id == id);
            if (news == null)
            {
                return NotFound();
            }

            _context.News.Remove(news);
            await _context.SaveChangesAsync();

            return Ok(news);
        }

        private bool NewsExists(long id)
        {
            return _context.News.Any(e => e.Id == id);
        }

        private bool CategoryExists(long id)
        {
            return _context.Category.Any(e => e.Id == id);
        }
    }
}