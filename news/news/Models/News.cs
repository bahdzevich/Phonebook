using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace news.Models
{
    [Table("news")]
    public class News
    {
        [Key, Column("id"), DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("title")]
        [Required, MaxLength(255)]
        public String Title { get; set; }

        [Column("content")]
        [Required]
        public String Content { get; set; }

        [Column("created")]
        public DateTime Created { get; set; }

        [Column("user_id")]
        public long UserId { get; set; }

        [JsonIgnore]
        public List<NewsCategory> NewsCategory { get; set; }

        public News()
        {
            NewsCategory = new List<Models.NewsCategory>();
        }
    }
}
